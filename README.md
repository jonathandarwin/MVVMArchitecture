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


## View
View is all the file that related to UI, for example : Activity, Fragment, XML.
View will control what will be displayed in the screen.
#### Note : the business logic (the logic flow of the page) should not be written here.


## View Model
View Model is the main component in this architecture.
View Model contain the main businees logic and other control that related to model.
View has reference to the View Model but the View Model has no reference to caller class.
The function of View Model is only provide the data to class that need the data.
#### Note : 1 View can have reference more than 1 View Model.

All the business logic in the View should be pass to the related View Model, View Model will process the logic
, return the result back to View, and the View will display the result to the screen.

### There are few library that we use to support this architecture :

## Data Binding
Data binding used to bind activity to XML. To implement databinding, add this line of code in gradle (Module Level)
        
        android {
        
            ....
            
            dataBinding {
                enabled true
            }
        }

#### The Advantage of Data Binding:
Usually we used findViewById() to get the object view from XML

        Button btnLogin = (Button) findViewById(R.id.btnLogin);

Data Binding will elimiinate this line of code. Instead we define the object one by one, we use 1 variable that represent
the XML. For example :

        LoginActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.login_activity);

#### Note : "LoginActivityBinding" will be automatically generated when we convert XML to databinding type.
Notice that without using data binding we already used this line of code, but without "DataBindingUtil" and the parameter "this"

        setContentView(R.layout.login_activity);

Data binding is very useful to reduce line of code. To access the object, we can use the variable binding
        
        binding.btnLogin.setOnClickListener(this);

Without databinding, we used to send value to from activity to XML with :

        TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtUsername.setText("jonathan");
        
With databinding, we use :
        
        LoginModel model = new LoginModel();
        model.setUsername("jonathan");
        model.setPassword("asdf");
        binding.setModel(model);

Databinding can let you set value directly from the model, so it will save many line of code.

#### That is the difference of data binding in the code. Let's see the use of data binding in XML :

    <layout>        
    
        <data>
            <variable
                name="model"
                type="packageName.LoginModel"/>                            
        </data>
        
        <LinearLayout>
        
            <TextView
                android:name="@{model.username}"/>
                
        </LinearLayout>
        
    </layout>

#### Note :
1. XML will always started with <layout> tag
2. Tag <layout> only contain 1 parent layout (except <data>)
3. Define variable that will be used in XML inside the <data> tag
4. <variable> have 2 attributes, "name" represent the name of the variable, "type" represent the data type of correspond variable
5. We can initiate more than 1 variable in 1 XML
6. @{model.username} used to access the value of variable "username" in the our model.
7. Actually, model.username will access the getter method in the model class "public String getUsername()", so make sure that the class contain the getter method for each attribute!

### The last, our model class will be like this :

        public class LoginModel{
            protected String username;
            protected String password;
            
            public void setUsername(String username){
                this.username = username;
            }
            
            @Bindable
            public String getUsername(){
                return username;
            }
            
            public void setPassword(String password){
                this.password = password;
            }
            
            @Bindable
            public String getPassword(){
                return password;
            }
        }

we add annotation "@Bindable" above the getter method in each attribute to specify that method is bind to the XML and can
be access directly from XML.

That's the review of data binding. The explanation isn't completed yet, but you can ask me if you have any question :).

#### Next, we use 
