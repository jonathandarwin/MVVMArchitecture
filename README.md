# MVVM Architecture
Google introduce the new architecture in android development called MVVM (Model View View Model),
which is one of the best architecture in android development aside from MVP (Model View Presenter) and MVI (Model View Intent)

This architecture contains the 3 main components : Model, View, and View Model.
So what is Model, View, and View Model?




## Model
Model is the class that represent the data in the view. the data that will be display in the UI or the data
that we get from the UI will be saved in this Model class.

For example :
Consider a Login Page that consists of textbox for username, password and the Button for login

    ==============================================
    |                                            |
    |                  Username                  |
    |              __________________            |
    |                                            |
    |                  Password                  |
    |              __________________            |
    |                                            |
    |                -----------                 |
    |                |  Login  |                 |
    |                -----------                 |
    |                                            |
    |                                            |
    ==============================================
from the UI, we know that we will hold the username and password value. so our view model should be like :


    public class Login{
    
        protected String username;
        protected String password;

        public void setUsername(String username){
            this.username = username;
        }

        public String getUsername(){
           return username;
        }


        public void setPassword(String password){
            this.password = password;
        }

        public String getPassword(){
            return password;
        }   
    }

#### So, the model class consists of attribute that related to UI and also the getter setter is necessary


# View
View is all the file that related to UI, for example : Activity, Fragment, XML.
View will control what will be displayed in the screen.
#### Note : the business logic (the logic flow of the page) should not be written here.


# View Model
View Model is the main component in this architecture.
View Model contain the main businees logic and other control that related to model.
View has reference to the View Model but the View Model has no reference to caller class.
The function of View Model is only provide the data to class that need the data.
#### Note : 1 View can have reference more than 1 View Model.
All the business logic in the View should be pass to the related View Model, View Model will process the logic
,return the result back to View, and the View will display the result to the screen.
