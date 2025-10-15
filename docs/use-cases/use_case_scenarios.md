 ## Use Case Scenarios — Code Review 2

 Use Case 1: Generate Country Report

Actor: Analyst  

Precondition: Database connected and contains country data.  

Main Flow:
1. Analyst selects “Generate Country Report.”  
2. System retrieves all country data from the database.  
3. System sorts countries by population.  
4. System displays the report in a readable format.  

Alternative Flow:  
- If no data is found, show “No country data available.”
  
Postcondition: Country report displayed successfully.

Use Case 2: Generate City Report

Actor: Analyst  

Precondition: Database connected and contains city data. 

Main Flow:
1. Analyst selects “Generate City Report.”  
2. System retrieves all city data.  
3. System sorts cities by population.  
4. System displays the report.  

Alternative Flow:
- If data missing, show “No city data available.”

Postcondition: City report displayed successfully.


Use Case 3: Sort Reports by Population

Actor: Analyst  

Precondition: Report already generated.  

Main Flow:
1. Analyst selects sorting option (ascending or descending).  
2. System reorders the results by population.  
3. System refreshes the view.  
Alternative Flow:

- If sorting fals, show “Unable to sort.”
  
Postcondition: Report displayed in chosen order.
