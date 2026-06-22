# Bank Account Simulation

## English

Bank Account Simulation is a Java 21 console application for practicing object-oriented programming, inheritance, service-layer logic, custom exceptions, transactions, and unit testing. The program simulates a small banking system where customers can register, open different account types, deposit money, withdraw money, transfer money between accounts, and view transaction history.

### Main Features

- Register customers by name.
- Open standard, checking, or savings accounts for registered customers.
- Store accounts both globally in the bank service and inside the owning customer.
- Deposit money into an account.
- Withdraw money from a standard account.
- Allow checking accounts to use an overdraft limit.
- Transfer money between accounts.
- Charge a fixed transfer fee.
- Save transaction history with transaction type, amount, date/time, source account, target account, description, and balance after transaction.
- Handle errors with custom exceptions, for example missing accounts, insufficient funds, invalid transfer amount, and same-account transfer.

### Account Types

`BankAccount` is the base account type. It stores the owner, account number, balance, and transaction list. It contains the common deposit, withdraw, transfer-in, transfer-out, fee, and transaction-recording behavior.

`CheckingAccount` extends `BankAccount` and adds an overdraft limit. Its withdrawal rule is different from a standard account: it can withdraw up to the current balance plus the overdraft limit.

`SavingsAccount` extends `BankAccount` and stores an interest value. The account type is already modeled, while more savings-specific behavior can be added later.

### Project Structure

```text
bankAccountSimulation/
  src/main/java/com/ramonafabri/projects/
    BankAccountSimulation.java          main class
    BankAccountSimulationEngine.java    console command handling
    Customer.java                       customer model
    account/                            account classes
    service/BankService.java            business logic
    transaction/                        transaction model and enum
    exceptions/                         custom exceptions
    ui/                                 UI abstraction and console UI

  src/test/java/com/ramonafabri/projects/
    account/                            account unit tests
    service/                            service unit tests
```

### Example Commands

The engine supports pipe-separated commands:

```text
rc | ramona
oa | ramona | 1000 | standard
d | 1000 | 500
w | 1000 | 100
oa | ramona | 2000 | standard
t | 1000 | 2000 | 50
h | 1000
h | 2000
q
```

Long command names also work:

```text
registerCustomer | ramona
openAccount | ramona | 3000 | checking | 200
deposit | 3000 | 100
withdraw | 3000 | 250
history | 3000
```

### Tests

The project uses JUnit 5. Current tests cover:

- Deposit behavior.
- Withdraw behavior and insufficient funds.
- Checking account overdraft behavior.
- Customer registration.
- Opening accounts through `BankService`.
- Deposits through `BankService`.
- Transfers with transfer fee.
- Transaction history.
- Account-not-found exception handling.

You can run the tests from IntelliJ IDEA using the green run button next to each test class or method.

### Learning Goals

This project is a portfolio-friendly Java practice project focused on:

- OOP and inheritance.
- Separating console input from business logic.
- Modeling domain objects.
- Working with lists and object relationships.
- Creating and using custom exceptions.
- Writing beginner-friendly unit tests.

## Magyar

A Bank Account Simulation egy Java 21 konzolos alkalmazás, amely objektumorientált programozás, öröklődés, service réteg, saját kivételek, tranzakciókezelés és unit tesztek gyakorlására készült. A program egy egyszerű banki rendszert szimulál, ahol az ügyfelek regisztrálhatnak, különböző számlatípusokat nyithatnak, pénzt fizethetnek be, pénzt vehetnek fel, utalhatnak számlák között, és megnézhetik a tranzakciós előzményeket.

### Fő Funkciók

- Ügyfél regisztrálása név alapján.
- Normál, folyószámla és megtakarítási számla nyitása regisztrált ügyfélhez.
- A számla eltárolása a banki service-ben és az ügyfél saját számlalistájában is.
- Pénz befizetése számlára.
- Pénzfelvétel normál számláról.
- Folyószámlán overdraft limit használata.
- Pénz utalása számlák között.
- Fix utalási díj felszámítása.
- Tranzakciós előzmények mentése típussal, összeggel, dátummal, forrásszámlával, célszámlával, leírással és tranzakció utáni egyenleggel.
- Hibakezelés saját kivételekkel, például nem létező számla, elégtelen fedezet, hibás utalási összeg és azonos számlára utalás esetén.

### Számlatípusok

A `BankAccount` az alap számlatípus. Tárolja a tulajdonost, számlaszámot, egyenleget és a tranzakciók listáját. Ebben található az általános befizetés, pénzfelvétel, utalás be, utalás ki, díjterhelés és tranzakciómentés logikája.

A `CheckingAccount` a `BankAccount` leszármazottja, és overdraft limitet tartalmaz. A pénzfelvételi szabálya eltér a normál számlától: a jelenlegi egyenleg és az overdraft limit összegéig enged pénzt felvenni.

A `SavingsAccount` szintén a `BankAccount` leszármazottja, és kamat értéket tárol. A számlatípus már modellezve van, később további megtakarítási logika is hozzáadható.

### Projekt Felépítése

```text
bankAccountSimulation/
  src/main/java/com/ramonafabri/projects/
    BankAccountSimulation.java          main osztály
    BankAccountSimulationEngine.java    konzolos parancsok kezelése
    Customer.java                       ügyfél modell
    account/                            számla osztályok
    service/BankService.java            üzleti logika
    transaction/                        tranzakció modell és enum
    exceptions/                         saját kivételek
    ui/                                 UI absztrakció és konzolos UI

  src/test/java/com/ramonafabri/projects/
    account/                            számla unit tesztek
    service/                            service unit tesztek
```

### Példa Parancsok

A program pipe karakterrel elválasztott parancsokat kezel:

```text
rc | ramona
oa | ramona | 1000 | standard
d | 1000 | 500
w | 1000 | 100
oa | ramona | 2000 | standard
t | 1000 | 2000 | 50
h | 1000
h | 2000
q
```

A hosszabb parancsnevek is használhatók:

```text
registerCustomer | ramona
openAccount | ramona | 3000 | checking | 200
deposit | 3000 | 100
withdraw | 3000 | 250
history | 3000
```

### Tesztek

A projekt JUnit 5-öt használ. A jelenlegi tesztek lefedik:

- Befizetés működése.
- Pénzfelvétel és elégtelen fedezet.
- Folyószámla overdraft logika.
- Ügyfél regisztráció.
- Számlanyitás `BankService`-en keresztül.
- Befizetés `BankService`-en keresztül.
- Utalás utalási díjjal.
- Tranzakciós előzmények.
- Nem létező számla kivételkezelése.

A teszteket IntelliJ IDEA-ban a tesztosztályok vagy tesztmetódusok melletti zöld futtatás gombbal lehet elindítani.

### Tanulási Célok

Ez a projekt egy portfólióba is illő Java gyakorlóprojekt, amely főleg ezekre fókuszál:

- OOP és öröklődés.
- Konzolos input és üzleti logika szétválasztása.
- Domain objektumok modellezése.
- Listák és objektumkapcsolatok használata.
- Saját kivételek létrehozása és használata.
- Kezdőbarát unit tesztek írása.
