# Model-View-ViewModel (MVVM)

## 1. What is Archtecture?
When it comes to architecture in software development, actually we talk about the foundation of the software. To simplify its meaning, we can refer it to house architecture. Before building the house, architect should design the foundation, make sure that the foundation is strong enough to be lived. But, why we need the architecture while we can build the house without it? Well, let's take a look at this scenario. In the next year, you want to built the second floor above your house. Of course, you should have to make sure that your house is strong enough to hold all the things that placed in the second floor. Not all the house architecture is designed to hold the second floor, let alone no architecture.
Let's back to software architecture. The scenario above also happen in software, for example add some new features, fix some bugs, etc. Without architecture, it is still possible to make the changes in the software, but it is harder than if we used a suitable architecture.

## 2. What is the Advantages?
- It is easier for your team to understand the code, because it is like a standardization of the software
- Easier to maintain the software
- Your code is reusable and scalable
- Can be easily tested

## 3. Introduction to MVVM
MVVM is one of the most used architecture in android development aside from MVP, MVI, etc. MVVM consist of 3 main parts : **Model**, **View**, and **ViewModel**. Each part has its own responsibility, and it will be discussed here.

### Model
Model is one of the component in MVVM architecture that responsible for handling the data that is used in your app. it can be referred as the domain object, that represent the actual data.
<br />
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

#### So, the model class consists of attribute that related to UI and also the getter setter method if necessary

### View
View responsible for any kind of interaction between the user and app. When the button is clicked, or user input an username in the app, View is responsible for handling that action and control what will be displayed in the screen. Activity, fragment are categorized as View in android. Please note that in this architecture, the view should not contain any business logic that used through your apps, including how the data fetched, where the source of your data, etc.

### ViewModel
ViewModel is the component that differ this architecture from others. ViewModel used as a bridge between the view and model. The data from model should be passed to ViewModel before it goes to certain view.
<br />
**Note : ViewModel should not have any reference to its view. It only provides the data, and the caller should subscribe to certain ViewModel. Because of this, ViewModel is survive from configuration changes**

## 4. Why MVVM?
- Loose coupling between presentation and business logic (ViewModel is not have direct reference to View)
- Android lifecycle friendly (ViewModel survived when configuration changes)
- Well documented in Google docs
- Part of Jetpack component (_Jetpack is a suite of libraries, tools, guidance to help developers write high-quality apps more easily.
source : https://developer.android.com/jetpack/_)


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

Data Binding will elimiinate this line of code. Instead of define the object one by one, we use 1 variable that represent
the XML. For example :

        LoginActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.login_activity);

#### Note : "LoginActivityBinding" will be automatically generated when we convert XML to databinding type.
Notice that without using data binding, we also used this line of code, but without "DataBindingUtil" and the parameter "this"

        setContentView(R.layout.login_activity);

Data binding is very useful to reduce line of code. To access the object, we can use the variable "binding"
        
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
1. XML will always started with \<layout\> tag
2. Tag \<layout\> only contain 1 parent layout (except \<data\> tag)
3. Define variable that will be used in XML inside the \<data\> tag
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
## Live Data
Live data makes your model can be observe. When observed data change its value, it will notify the observer that correspond data is change and the observer can do something to respond that change. For example :
       
       MutableLiveData<String> username = new MutableLiveData<>();
       
       username.observe(this, new Observer<String>(){
            @Override
            public void onChange(@Nullable String username1){
                // do something
                // username value : jonathan
            }
       });
       
       username.setValue("jonathan");

#### Note : Because variable with LiveData type cannot be change, we use MutableLiveData. This two type has the same functionally, except the MutableLiveData value is mutable :)

Explanation :
First, we declare a variable named "username" with type "MutableLiveData\<String\>". Simply, "username" is a MutableLiveData that has string value.
Notice that we observe the change of the "username" variable in this line of code :
    
        username.observe(this, new Observer<String>(){
            @Override
            public void onChange(@Nullable String username1){
                // do something
                // username value : jonathan
            }
       });
       
When the value of "username" variable is changed, it will trigger this line of code and run the code inside "onChange".
The listener will triggered when this line of code is executed :
        
        username.setValue("jonathan");
        
The "username" variable value is set to "jonathan", means that the value is changed. Then it will fired the listener that
we have already declare before.
Not only variable, we can observe the method too.

    public LiveData<String> processingUsername(){
        MutableLiveData<String> username = new MutableLiveData<>();        
        return username;
    }
    
and the code in another class that act as the listener
    
    processingUsername.observe(this, new Observer<String>(){
        @Override
        public void onChange(String username1){
            // do something
        }
    });

The code is still the same as before :)

LiveData is applied in MVVM, especially in View and View Model. View Model doesn't have the reference to the caller class, it only set the value and the caller class (usually activity) will listen if there are any changed in that data that the caller needed.

        View (Observer) ---- Observe ----> ViewModel        

Again, this explanation is not complete because it only cover the basic knowledge and how to use the livedata, not all the knowledge of using live data.
