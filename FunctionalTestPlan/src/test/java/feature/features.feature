Feature: Basic Functionality Testing

Scenario: Retail Ops without upgrade
Given Initialize the browser with chrome
And  Redirect to the ASGC "http://localhost:8080/altierre/asg"
And login into ASG with username "asgadmin" and password "asgAdmin!"
And Click on retail ops menu
When Select "Ordering" and enter "20" value
Then Retail ops batch should created
And close the browser

Scenario: Retail Ops with upgrade
Given Initialize the browser with chrome
And  Redirect to the ASGC "http://localhost:8080/altierre/asg"
And login into ASG with username "asgadmin" and password "asgAdmin!"
And Click on retail ops menu
When Select "Price" and enter "50" value
And Upgrade the ASG
Then Retail ops batch should created
And close the browser


Scenario: Quarantine Feature
Given Connect to database and fetch the value Before ASG Restart
And Restart the ASG
Then Connect to database and fetch the value After ASG Restart
And Compare the value

Scenario: Wireless Sync Feature
Given set wireless sync feature in customer properties
And Initialize the browser 
When Redirect to ASGC "http://localhost:8080/altierre/asg"
And login in ASG with username "asgadmin" and password "asgAdmin!"
Then Add Wireless sync with name "Sync" and description "Description" and perform other action
And Close the browser

Scenario: Autoconfig AAP
Given Initialize browser
And Redirect to ASG console "http://localhost:8080/altierre/asg"
And Login into ASG with username "asgadmin" and password "asgAdmin!"
And Click on admin menu
Then Perform Autoconfig function



