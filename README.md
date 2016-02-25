# refactoring by Henry Patrick Karugendo

Paddy's Assignment


1. Created packages
------------------------
  - for organisation as I would be adding a few more classes and might 
  - might also decide to keep the old classes for better comparison in case I misse something
  - it is also considered convention to use packages


2. Created Separate Classes
------------------------
  a. Validator
    - this is used to validate entries as numbers 
    - and to also convert doubles to currency using NumberFormat
    
  b. Generators
    - this generates passwords using names
    - also generates ids
    - might also use it to make 4 digit pins 
    
  c. AlertBuilder
    - Used to create AlertDialos
    - Intended to use it for all types of dialogs but again, more complexity would have been introduced
    - we want to make things better not worse after all
    - No inpit dialogs therefore, only message dialogs
    
  d. ViewCreator
    - was originally meant to create frames and panels for the Main class
    - ended up being my version of the Main class
    - passing elements between classes would have been unnecessary coplication

    
3. Cleaned up the Main class as a new class - ViewCreator
---------------------------------------------------------
