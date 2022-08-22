# WeatherService
--WeatherInforepositoryLib and WeatherInfoBackupRepositoryLib projects are repository libraries which are used other projects.
--PlaceInfoCoordinateInserter's duty is to take place's coordinates by a web service and insert into daily servise database.
--PeriodicWeatherInfoCollector takes periodically places' coordinates from daily service database and saves weather information using by "http://api.geonames.org" website to daily 
  service database.
--WeatherInfoDailyService is a daily service that takes daily weather information from daily database.
--WeatherInfoBackupApp's duty is taking datas from daily database and saving backup database.
--WeatherInfoHistoryService represents old weather information which is taken from history database.
