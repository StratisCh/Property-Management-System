# Property Management System

## Προαπαιτούμενα

Βεβαιωθείτε ότι τα παρακάτω είναι εγκατεστημένα στο σύστημά σας πριν προχωρήσετε:

- **Java Development Kit (JDK)** 
- **Maven** (για τη διαχείριση εξαρτήσεων)
- **Git** (για την κλωνοποίηση του αποθετηρίου του έργου)

## Εγκατάσταση και Ρύθμιση

Ακολουθήστε αυτά τα βήματα για να εγκαταστήσετε και να εκτελέσετε την εφαρμογή τοπικά.

### Κλωνοποίηση του Repository

Ανοίξτε το τερματικό σας και εκτελέστε την παρακάτω εντολή για να κλωνοποιήσετε το αποθετήριο:

```bash
git clone https://github.com/StratisCh/Property-Management-System
```
### Μεταβείτε στον Κατάλογο του Έργου
```bash
cd Property-Management-System
```
### Εκτελέστε την Εφαρμογή

Ξεκινήστε την εφαρμογή χρησιμοποιώντας το Maven:
```bash
mvn spring-boot:run
```
Η εφαρμογή θα ξεκινήσει έναν server και μπορείτε να την ανοίξετε στον browser σας στη διεύθυνση:
http://localhost:8080

### Δημιουργία Πρώτου Διαχειριστή (Admin)
Μετά την αρχική εγκατάσταση της εφαρμογής, θα χρειαστεί να δημιουργήσετε τον πρώτο διαχειριστή για να έχετε πλήρη πρόσβαση στις λειτουργίες της εφαρμογής.

Αν θέλετε να το κάνετε αυτό μέσω του java κώδικα:

* Εγγραφτείτε και συνδεθείτε στο site ως ένας κανονικός χρήστης.

* Εκτελέστε τον παρακάτω κώδικα σε κάποιον controller που έχουν όλοι οι χρήστες πρόσβαση
* Αναβαθμίστε αυτόν τον χρήστη σε διαχειριστή (Admin):
```bash
Profile profile = profileController.getLoggedInUserProfile();
        User user = profile.getUser();
        Optional<Role> role = roleService.getRole("ROLE_ADMIN");
        user.getRoles().add(role.get());
        userService.updateUser(user);
```
* Τέλος διαγράψτε αυτό το κομμάτι κώδικα.

## Δομή του Έργου
* src/main/resources/templates/: Περιέχει τα πρότυπα Thymeleaf για το frontend.
* src/main/resources/static/css/: Περιλαμβάνει το CSS πρότυπο από το Bootstrap.
* src/main/java/: Ο πηγαίος κώδικας Java για το backend του Spring Boot.


## Environment variables

* spring.datasource.username=dsuser
* spring.datasource.password=yX3nR3Olt7xxs62GtYcOmIG9aJ6QMGY4
* spring.datasource.url=jdbc:postgresql://dpg-ctnj5pdds78s73c5cco0-a.oregon-postgres.render.com:5432/dbname_e18f