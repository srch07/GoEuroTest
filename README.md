# GoEuroTest
GoEuro - Test assignment

## How to run it ?

Download the jar from https://github.com/srch07/GoEuroTest/blob/master/archive/GoEuroTest_1.0.jar

Execute the jar like "java -jar GoEuroTest_1.0.jar london"

## Where is the generated CSV file being saved?

CSV file is getting generated in D:/GoEuroTest.CSV

## How do i make generated CSV file to save in different directory?

Edit com.go_euro.test.utility.Constants.CSV_FILE_PATH

## How do i make file append instead of overwritting each time?

Edit com.go_euro.test.utility.Constants.APPEND_ALLOWED_TO_SAVE_CSV_DATA

## Anything else you want to mention?

I have implemented caching of API calls using in-memory cache. 
But right now, it doesn't work because after each iteration jar execution shutsdown and needs to be fired again.
There is no requirement currently, to make program run in loop.
