# Groceries Tracker

### A smart way to keep track of costs and organize meals.

###### A quick synopsis of the application:
    



**What will this application be capable of?**:
- Keeping track of groceries expenses by tallying up individual item costs.
- Keep a record of your average daily, weekly and monthly groceries expenses
- Keep track of your average single meal cost.
- Record, and keep track of expiry dates and remind you when you have food that's about to expire.
- Be able to create a weekly meal plan and create automated shopping lists based on what you have and what you still need.

*Optional/Extra features I will add if time allows*:
- Keep track of where individual items are within your fridge/freezer/pantry to find them easily using a GUI.
  (This is useful if you share a fridge with roommates)

**Who will use it?**
- Individuals who cook alone and want to be better organized financially. 
- Able to save money and the environment by not letting food go to waste by seeing all the expiry dates easily
- Able to see with a click what you already have and how much so, you can ensure you won't buy duplicates of things you already have at the store. 


**Why is this project of interest to me?**
- I'm living in a house with friends, and I've realized that I quite often forget what I already have in my fridge. 
This has led to things going bad and me having to throw them out. Forgetting what I have also sometimes leads to me buying duplicates at the store because I'm not sure what I already have at home.


## User Stories
- As a user, I want to be able to create a new item bought at the grocery store
- As a user, I want to give that item bought a price tag, expiry date (if any) and serving size
- As a user, I want to be able to check when an items' expiry date is
- As a user, I want to be able to use an item and note it down in the tracker
- As a user, I want to know if an item is expired
- As a user, I want to know how many portions of an item I have left
- As a user, I want to know what is in my fridge, freezer and/or pantry
- As a user, I want to know how much is left in my budget
- As a user, I want to know how much of my budget I have already spent

## P2 User Stories
- As a user, I want to save the state of my grocery tracker, including what items I have and my current budget
- As a user, I want to be able to reload a saved state of the tracker and continue using the tracker from a checkpoint

# Instructions for Grader

-You can initialize/start the grocery tracker by clicking, create new tracker on the splashscreen and inputting a monthly budget. In form dollars.cents

- You can generate the first required event related to adding Xs to a Y by clicking add new grocery item and adding a perishable or non-perishable food item with correct details/
- You can generate the second required event related to adding Xs to a Y by clicking display grocery items and filtering by the storage location of the grocery items/
- You can locate my visual component on the initial splashscreen (can't miss it)
- You can save the state of my application by clicking the save button
- You can reload the state of my application by either loading it when prompted on the splashscreen or clicking the load button