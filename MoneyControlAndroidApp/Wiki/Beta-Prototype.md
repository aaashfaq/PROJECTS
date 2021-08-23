# PennyWise

# Team code_squad

### ISEE 2020 Beta Prototype 

<img src="uploads/b30c28118c3457c01734c219360dff31/Logo.png" width="40%" height="40%">

### Overview

We have reached very close to the end of our application development. All the user stories were implemented into working prototypes. Hence, we have reached phase in the cycle where our application needs rigorous testing so as to provide a seamless and unparalleled experience to our customers. We have used various testing methods are all these are explained in great detail in this blog.


### General Test Process

As far as our testing is concerned, we have followed Condition Coverage Technique and Branch Coverage Technique for the White Box testing and for Black Box testing, we have followed the equivalence partitioning technique:


![General_test_process](uploads/2435c786f659a0b93be2c045d636ec1c/General_test_process.jpg)


### Testing Process

Major testing techniques which are available at our disposal are the following:

- **Unit Testing** - Testing is carried out by the developer after the completion of a module or functionality.
- **System Testing** - Testing is carried out by a test analyst only for the module or functionality
- **System Integration Testing**- Testing is carried out by a test analyst after all the modules are integrated.
- **Acceptance Testing** - Testing is carried out by a user for the entire functionality of the application or module.

White box comprises of Unit testing and other techniques such as System Testing and Acceptance Testing, Integration Testing are classified under Black Box Testing.



### White Box Testing

![Whitebox](uploads/c2d4c100ede729296ab1e207f4267d64/Whitebox.jpg)

This method of testing is from the developers' point of view. It enables the developer to check the flow of control of the line of codes through various loops and code structures. For the developers to use this method, they can follow the below two simple steps:

- First to get the complete working knowledge of the written code.
- To select the most suitable coverage technique, that is applicable to the written code.



### White Box Testing Techniques

There exist many coverage techniques for the developers to scrutinize blocks of written codes. They are:

- **Statement Coverage** - Tests each statement at least once. The main idea is to cover all executable statements inside the given block of code and check them with test cases.
- **Function coverage** -Tests each function at least once.
- **Edge coverage** -Tests every edge in the control flow graph in the program at least once
- **Branch coverage** -Tests every control statement in the program at least once.
- **Condition coverage** - Tests every boolean sub-expression whether evaluated to both true and false.

The following classes were tested using White Box Testing:



#### Field Validation while saving transactions:

##### Category Class Validation

![Category_valid](uploads/c5e83fdd570cc6b54a062e1aa6879589/Category_valid.jpg)

##### All mandatory transaction fields validation

![Transaction_fields](uploads/be428173089307985d2411ccb9fa46f2/Transaction_fields.jpg)



#### Field Validation while authenticating user:

##### Registration of new user validation

![Register_valid_new](uploads/42c5935281d20865e3e707a133dde119/Register_valid_new.jpg)



##### Login validation

![Login_valid_copy](uploads/8989f3feccd1483f97618f53cff93dcf/Login_valid_copy.jpg)


##### Save Transactions

![save_transaction](uploads/a54e43c73ff226b473f6b76101d4b3a6/save_transaction.jpg)



##### Delete Transactions from DB

![delete_transaction](uploads/8662b862fc05720b012ea8ca6e4a3ddd/delete_transaction.jpg)



##### Export data

![export_data](uploads/b6c38fbe701d717233753d8f1d99e6e4/export_data.jpg)


##### Contact Developers via Email

![contact_developer](uploads/434b21b776210e2fab9442142cdf85d2/contact_developer.jpg)


##### Summary of classes covered under White-Box Testing

![SummaryClasses](uploads/70fc91273e70a5900c97ffe5774cfbbb/SummaryClasses.png)



### Black Box Testing 

Black Box Testing is a testing technique in which the functionality of the application is tested without knowing the internal code or the underlying structure. The tests can be functional or non-functional.


![blackbox](uploads/e287d77373d7767877d9c05945e76b42/blackbox.png)


### Types of Black Box Testing

- **Functional Testing:** Usually performed by Test Analysts to check the functionality of the application based on the requirements received

- **Non-Functional Testing:**This testing focuses on the performance, usability, and scalability of the application

- **Regression Testing:** Testing done immediately after making any changes to the source code whether to know if all the earlier working functionalities are in place and has no impact on any of the functionalities done earlier

  

### Black Box Testing Types

- **Equivalence Partitioning:** It is a test design technique that involves dividing input valid and invalid partitions and selecting particular values from each partition as test data.

- **Boundary Value Analysis:** It is a test design technique that involves the determination of boundaries for input values and selecting values that are at the boundaries and just inside/outside of the boundaries as test data.

- **Cause-Effect Graphing:** It is a test design technique that involves identifying the case and effects producing a Cause-Effect Graph.

  

### Test Scenarios

![TestScenarios](uploads/d714948f48f9009b4715f90c7f76220d/TestScenarios.png)



### Test Cases

![tc1](uploads/dc4a201032a126dc39195aa27d5ab07d/tc1.png)

![tc2](uploads/a82ef503cd70d1e5b34c651d26de1dfb/tc2.png)

![tc3](uploads/b16e335514ecdb4ac6e56b7bad4d10fe/tc3.png)

![tc4](uploads/9162058959a2b764a77e5c7d50ce7a46/tc4.png)

![tc7](uploads/084c6531c2005601b5314b41dab75273/tc7.png)

![tc6](uploads/0069af89f8af3bcabed5dbb2abad45ec/tc6.png)


### Status and Defect

![status_defect](uploads/bd076f6f43ab70cd466a9c66af3b78a8/status_defect.png)

### Summary of Changes

##### Application Screenshots of Beta Prototype

![beta_collab](uploads/27bf6f2b16128c322e3d9d55ef8ad18e/beta_collab.png)
​
![beta_collab2](uploads/2b9c4398ebe87ce6376b1928227b34bd/beta_collab2.png)

| New Features               | Description                                                  |
| :------------------------- | :----------------------------------------------------------- |
| **Export Data**            | The transactions data created by the user can be exported to csv format for user to open the file as a sheet. |
| **Contact Developer Form** | User will be able to contact developers regarding any issue / feedback to be given. User will be required to provide information with some details such as user's contact number, location, name and message. After submitting the form, his mailing application will be opened with automatic filled in fields and user has to send this email. |
| **Help FAQs**              | For the ease of understanding the application especially to new users, user can go to Help and view some put in FAQs. |
| **Delete Transactions**    | User can get rid of expenses / transactions that were created by user, if user no longer needs them |
| **View Summary and Graph** | User can view a graphical layout like pie chart for better understanding his expenses. |
| **Push Notifications**     | User can now get push notifications when his expenses get exceeded by income. |
| **Cosmetic Changes**       | The application is now redefined for user to have a simple and friendly user experience. Alignment updates, icons, graphs have been added to give application an insightful power. |



### Download our application here by clicking on this link
[PennyWise.apk](uploads/76b6ca23c81fcf7cfef6258c67154119/PennyWise.apk)




### References

**Fig 13:** [https://www.guru99.com/black-box-testing.html](https://www.guru99.com/black-box-testing.html)


### *Thank you for visiting our blog!*











