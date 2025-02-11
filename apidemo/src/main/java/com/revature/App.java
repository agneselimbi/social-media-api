package com.revature;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Hello world!
 */
public final class App {
    private App() {
    }
    //private static final Logger logger = LoggerFactory.getLogger(App.class);
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        //Register user
        app.post("/register", App::registerUser);
        
        //Login user
        app.post("/login",App::loginUser);
        
    }

    public static  void registerUser(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password"); 

        if(username == null ||  password ==  null){
            ctx.status(400).result("Username and password required   for registration \n");
            return ;
        }
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        //check if user exists
        if (userDAOImpl.get(username) == null){
                ctx.status(400).result("Username already exists in the system. Please try again \n");
                return;
            }
            
        User newUser = new User(username,password);
        userDAOImpl.save(newUser);
        ctx.result("User successfully added to system. \n");
    
        }
    

    public static void loginUser(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        if(username == null ||  password ==  null){
            ctx.status(400).result("Username and password required \n");
            return ;
        }
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        try   {
            if (userDAOImpl.get(username).getClass()==User.class){
            User returnedUser = userDAOImpl.get(username);
            if(!returnedUser.getPassword().equals(password)){
                ctx.status(401).result("Password is incorrect! Please try again \n");
                return ;//
                }
            }else{
                ctx.status(401).result("Username and/ or  Password is incorrect! Please try again.");
                return ;
            }

            //Store user  session
            ctx.sessionAttribute("user",username);
            ctx.result("Login successful");
        }catch(NullPointerException e){
            //e.printStackTrace();
            ctx.status(401).result("Username  incorrect. Please try again. \n");
            return;
        }
    
}
}
