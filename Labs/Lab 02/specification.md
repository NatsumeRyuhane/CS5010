# Lab 02. Role Playing Games

In many [role-playing games](https://en.wikipedia.org/wiki/Role-playing_game), characters go into battle with some degree of attack and defense capabilities (represented as numerical values). **These values are calculated based on the character's base attack/defense (those values don't change after initialization)**, plus “wearing” different articles of clothing to either increase performance (attack) or minimize damage inflicted by other characters (defense). In this assignment, you are to represent the following types of clothing.

1. **Head gear**: These items go on the character’s head (hats/helmets/visors) and are only useful for defense.

2. **Hand gear**: These items go on the character’s hands (gloves/swords/shield) and are only used for attack. Since you have 2 hands, you can have 2 of these items.

3. **Footwear**: These items go on the character’s feet (boots/sneakers/hoverboard) and can be for attack or defense. Since you have 2 feet, you can have 2 of these items.

Each item has a name consisting of an adjective and a noun, in that order, and the amount that it modifies the character’s attack and defense values.
Combining Items

When the game starts out, the characters start with an attack power and a defense strength. As they go through the game, they can pick up new items to add to those two values. The following rules apply:

- They cannot pick up more items that they can hold. This means that they are limited to:

  - One piece of head gear

  - Two pieces of footwear

  - Two pieces of hand gear

- To be clever, when a character picks up two items of the same type, their names are combined, they make a new piece of footwear that combines the powers and name. (Tom's note: let's make the assumption that combining only happens when the player doesn't have empty slot for that gear) The new name is the adjective from one item and the full name from the other. For instance, to combine

  - Scurrying Sandals -- defense strength: 1, attack strength 0

  - Happy HoverBoard -- defense strength: 3, attack strength 1

  You get

  - Scurrying, Happy HoverBoard -- defense strength: 4, attack strength 1

    > Tom's note:
    > starting from Fall 2024, making the algo more complex -- the item with the higher attack gets to preserve its full name, while the lower one only contributes its adjective. If the two have the same attack, compare the defense; if the same defense, pick a random one

- Only items of the same type have their names combined.

## Part 1 – Design

Before you start to write code, it is a good idea to design your solution. To do this, you need to understand what your program needs to do, decide what classes you will need, and what methods each class will need. It is a really good idea to think about how each of theses methods and classes could be tested to ensure the correctness of your implementation. Thinking about this early will make the coding process much easier. To help you with your design process, you are required to meet with your TA during the design meeting. You must bring your completed design and test plan to this meeting and be ready to explain why your design is the right way to solve the problem. Each design meeting will last approximately 15 minutes.

### What to do

Design the data for the above in a way that captures their similarities and accurately represents the relevant data. Create interfaces/classes as you see fit in a way that allows one to create an outfit as specified above.

Write a testing plan that thoroughly tests your design. How do your tests convince someone else that your code works correctly? For each test in your design, you should specify what condition you are testing, what example data you will use to test that and what values you might expect a method to produce (the expected value) when appropriate.

## Part 2 - Development

### What to do

Implement the class hierarchy that you specified in Part 1. Rather than creating a whole game, create a driver class that represents a battle. During a battle, two characters get total 10 possible items to choose from. This new class should:

- Take two characters and a list of items as arguments to its contructor.

- The two characters take turns to dress themself. Each turn, the character can only choose one item. After an item is chosen, it is removed from the following turns. The items should be chosen based on:

  - Rule 1: Prefer the type of item that the character has available slot for. For example, if the character already has 2 footwear, 1 hand gear and 1 helmet, the character should try to choose 1 more hand gear.

  - Rule 2: if rule 1 leads to multiple choices, pick the item has the highest attack strength

  - Rule 3: If there is still a tie after Rule 1 and 2, pick the item has the highest defense strength
  - Rule 4: if there is yet still a tie after Rule 1,2,3, pick a random one.

- For each turn, Print out each character in the fight along with what they are wearing and their attack and defense strength

- After all the 10 items are chosen, Print out who is the winner of the battle. The winner is determined by who has less damage after a battle. Damage is calculated by one's opponent's attack power minus that character's defense points. For example:

  1. Player 1 has 8 attack points and 6 defense strength.

  2. Player 2 has 4 attack points and 7 defense strength.

  3. The battle ends with Player 1 having -2 units of damage and Player 2 having 1 unit of damage.

  4. Player 1 wins.

  If there is a tie, it should be stated as such.

### Documentation

We expect your code to be well-commented using well-formed English sentences. The expectations are:

- Each interface and class contains a comment above it explaining specifically what it represents. This should be in plain language, understandable by anybody wishing to use it. Comment above a class should be specific: it should not merely state that it is an implementation of a particular interface.

- Each public method should have information about what this method accomplishes (purpose), the nature and explanation of any arguments, return values and exceptions thrown by it and whether it changes the calling object in any way (contract).

- If a class implements a method declared in an interface that it implements, and the comments in the interface describe this implementation completely and accurately, there is no need to replicate that documentation in the class.
