# My Teams

My Teams is a MVVM app that query data from [TheSportsDB.com](https://www.thesportsdb.com/) and saves it in Room database.

## API key setup

Api Key was obtained from [TheSportsDB.com](https://www.thesportsdb.com/api.php),

- follow these steps to setup the API Key.


1. add `gradle.properties` into `.gitignore` file

2. Place Api key in `gradle.properties` file

  ```
   API_KEY="your pixabay api key"
  ```

3. Add following line in app level `build.gradle` file,

  ```gradle
  android {
   ...
   defaultConfig {
   ...
       buildConfigField("String", "API_KEY", API_KEY)
   }
  }
  ```

4. Use API key within code using `BuildConfig`

  ```
   val API_KEY :String = BuildConfig.API_KEY
  ```

## License


MIT