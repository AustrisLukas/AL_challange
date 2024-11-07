Prime Checker App.

Process:
1. On start up, app will look for cache.txt to populate cache. If not found FileNotFound exc will be caught by CacheUpdater.
2. Loop Show menu()
3. Option 1  - check if input is valid, proceed if OK. Check if input available in cache is true - display Prime numbers. If false, proceed to process new input, display prime numbers and write to cache.txt file.
4. Option 2 - read cache.
5. Option 3 - Quit app.

Further development: 
USERNAME implementation instructions ambiguous, clarification needed
Junit requires more test cases.
Utilise Logger to log events.
CacheUpdater reader sensitive to unexpected cache.txt format.


Known issues:
cache.txt contains only unique entries, however getCombinations() may produce non-unique prime candidates eg [input]1111 =  Prime Candidates: [1, 1, 1, 1, 11, 11, 11, 111, 111, 1111]

austris.lukas@icloud.com
7NOV2024

