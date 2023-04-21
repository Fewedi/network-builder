
## QUCKSTART GUIDE

### 1: Stellen sie sicher, dass Java 17 installiert ist:
> java -version

### 2: Downloaden sie den Ordner Quickstart von: https://drive.google.com/drive/folders/1bnEE24q2TFhXJqPnZSFEAFNUpKId_kCm?usp=share_link

### 3: Anpassen der Pfade in der application.properties an:

Passen sie om Ordner Quickstart in der **application.properties** die folgenden Pfandspezifizierungen an:
default.**path_data_household** muss in den Ordner **data** führen. 
>default.path_data_household=/path/to/Quickstart/data
 
default.**path_data_age** muss in den Ordner **data** führen.
>default.path_data_age=/path/to/Quickstart/data

default.**path_data_worksites** muss in den Ordner **data** führen.
>default.path_data_worksites=/path/to/Quickstart/data

default.**path_output** muss in den Ordner **output** führen.
>default.path_output=/path/to/Quickstart/output

Passen wie bei Bedarf auch weitere Parameter an.

### 4: Durchführen des Programms
Öffnen sie die Commandline und navigieren sie in den Ordner **Quickstart**. Starten sie die Jar-Datei:

> java -jar network-builder-0.0.1-SNAPSHOT.jar

Wenn "**----------EVERYTHING DONE----------**" ausgegeben wird, ist die Simulation beendet und das Programm kann gestoppt werden. Die Resultate sind je nach Spezifikation in einem oder mehreren Ordnern im Ordner Output zu finden.

## DATENGENERIERUNG

Die Daten, die das Modell verwendet um die Haushalts- und Altersstruktur der Agenten zu generieren stammt von: http://infuse.ukdataservice.ac.uk/

### Generierung Daten
- 1: Gehe auf **2011 Census data UK**
- 2: Gehe auf **Geography**
- 3: Gehe aufs **+** von **Local Authorities (32 areas)** bei **Scottland**
- 4: Gehe aufs **+** von **Glasgow City**
- 5: Wähle die Kästchen links neben **Glasgow City** und **Lower Super Output Areas and Data Zones (746 areas)** aus
- 6: Wähle **Add** unten auf der Seite und bestätige it **Next**
### bei Altersdaten
- 7: Wähle Kästchen **Age** und bestätige mit **Next**
- 8: Wähle alle 100 Kästchen nach dem Schema **Age under 1**, **Age 1**, **Age 2**, **Age 3**, **Age 4**, **Age 5** ... , **Age 99**, **Age 100 and over** (nicht die kästchen Age a to b)
- 9: Wähle Kästchen **Persons** 
- 10: kicke **Add** und bestätige mit **Next**
- 11: klicke **Get the Data** und **Download Data**
### bei Haushaltdaten
- 7: Filtere links nach **Household type [E][S][W]** 
- 8: Wähle Kästchen mit **• Age • Dependent children in household • Household type [E][S][W] • Population (usual residents)** und bestätige mit **Next**
- 9: Wähle alle Kästchen unter **Age**
- 10: Wähle die Kästchen **No dependent children in household** und **One or more dependent children in household**
- 11: Wähle die Kästchen **Living in a couple household** und **Not living in a couple household**
- 12: kicke **Add** und bestätige mit **Next**
- 13: klicke **Get the Data** und **Download Data**

## VISUALISIERUNG

Die Pythonskripte sollen den Output entsprechend der Darstellung in der Ausarbeitung visualisieren. Die Art der Visualisierungen hängt somit davon ab, welche der Konfigurationen **true** ist:
- test_multiple
- test_transmission_probability
- test_population
- test_testing_behaviour
- keins (es gibt also nur einen Durchlauf)

### Replizieren der Visualisierungen der Ausarbeitung

Im Ordner Visualisierungen sind Ordner zu allen Darstellungen in der Ausarbeitung enthalten: https://drive.google.com/drive/folders/1bnEE24q2TFhXJqPnZSFEAFNUpKId_kCm?usp=share_link
Jeder Ordner enthält 
- zugundeliegende Output
- application.properties (als Referenz)
- die beiden Python-Skripte (dies ist jedes Mal die gleiche Datei)
- die Darstellungen

##### 1: Download von einem der Ordner
##### 2: Durchführen des Skriptes
Durchführen der main.py im Ordner:
> python main.py



### Durchführen der Visualisierung auf generierten Daten

##### 1: Zusammenführen der Dateien und Skripte
Es müssen im gleichen Ordner liegen:
- main.py
- fileprocesser.py
- Order mit dem Namen **data** der alles enthält, was von der Simulation in den output-Ordner gelegt wurde

##### 2: Durchführen des Skriptes
Durchführen der main.py im Ordner:
> python main.py