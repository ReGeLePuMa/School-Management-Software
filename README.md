# Catalog


Pentru rularea mai facila a temei am adaugat pe langa cele 2 directoare SURSE/ si PROIECT/
si fisierul README.pdf un Makefile cu 4 reguili:

- **_clean_** – sterge toate fisierele din PROIECT/
- **_build_** – compileaza toate fisierele din SURSE/
- **_test_** – realizeaza testarea arhitecturii temei prin afisarea la stdout
- **_run_** – realizeaza testarea interfetei grafice

# Implementarea temei

## _1. Arhitectura temei_

_Observatie_ : _Toate interfetele pe care a trebuit sa le creez(Visitor, Strategy etc) au in fata
numelui litera I pentru a ajuta la lizibilitate._

Clasa Catalog a fost implementata ca Singleton, inautrul careia, pe langa lista de materii, am
adaugat o lista de “abonati” la catalog si un dictionar in care retin mesajele fiecaruia(Pentru
interfata grafica).

Clasa UserFactory foloseste o enumeratie pentru a specifica tipul de utilizator(Student,
Parent, Teacher si Assistant).

Pentru clasa Group am definit o clasa SortedList generic care mosteneste
ArrayList(SortedList o voi folosi si pentru Grades unde nu merge sa folosesc TreeSet din
cauza modului de comparare unde am facut un caz special la adaugare doar pentru cazul in
care SortedList continue elemente de acest tip i.e. sa pot adauga doi Studenti cu acceasi
nota si sa nu mai adauge un Grade nou ci sa modifice Grade studentului pe care il vreau sa
adaug).

Clasa Course a fost cea mai grea de realizat datorata modului esoteric de implementare a
Builderului (clasa interna abstracta si nu o clasa dedicata ce implementeaza o interfata). In
final am reusit sa-l creez si metodele din el au fost create facil. La fel si pentru clasele fiice
PartialCourse si FullCourse si Builderele lor aferente. Tot aici am implementat Memento-ul,
creeand o clasa ce cloneaza fiecare Grade si stocheaza intr-o lista si Strategy.


Pentru Observer am ales Observer clasa Parinte si Subject clasa Catalog.

Pentru Visitor am ales clasele Assistant si Teacher ca Element. In ScoreVisitor am create cele
doua dictionare, trimitand notificari parintii cand studentulului i-a fost validata nota(daca
aceasta nu se afla deja in dictionar). Totodata, aici am create si clasa Tuplu generica.

## _2. Interfata grafica_

Pentru interfata grafica am creat 2 cursuri SO – FullCourse si POO – PartialCourse si
urmatorii utilizatori:

- Studenti
    ▪ Belea Spartanul – SO
    ▪ Denji Asa – SO
    ▪ Ionel Ionescu SO si POO
    ▪ Caen Kaktus – POO
    ▪ Beton Armand – POO
- Parinti
    ▪ Mama Ionescu
    ▪ Tata Ionescu
- Profesori
    ▪ Profesor Pacate – SO
    ▪ Karen Yoru – POO
- Asistenti
    ▪ Sas Tufis – POO si SO

### Login

Am creat o interfata grafica de logare in care utilizatorul isi introduce email-ul si parola in
felul urmator:

![alt text](https://scontent.fcra2-1.fna.fbcdn.net/v/t1.15752-9/334881689_959416835057231_6435781668458363598_n.png?_nc_cat=100&ccb=1-7&_nc_sid=ae9488&_nc_ohc=BqCefQRqQoQAX-d1rz4&_nc_ht=scontent.fcra2-1.fna&oh=03_AdR-_5XgCX5M7DOSPc8KYvadHZXPHMamAu3Y0EQ7nigWhw&oe=6436FB41)


Daca nu corespunde rolul lor cu domeniul adresei de email sau email-ul/parola este gresita
atunci va aparea mesajul cu autentificarea invalida

![alt text](https://scontent.fcra2-1.fna.fbcdn.net/v/t1.15752-9/335708239_862611391473325_7561230854877190093_n.png?_nc_cat=105&ccb=1-7&_nc_sid=ae9488&_nc_ohc=OM76vPT0h8AAX831w8c&_nc_ht=scontent.fcra2-1.fna&oh=03_AdTiG8k_z7_iHzOtv-FWJ-Yo-EISGjxKSXXdfJ0rd2wkPA&oe=64370F51)

Odata autentificat, acestuia i se va afisa pagina corespunzatoare cu rolul acestuia.

_Nota: Inchiderea paginii de logare va duce la_ **_terminarea programului_**_! Puteti in schimb sa
inchideti celelalte ferestre fara probleme._

_Nota: Pentru ca schimbarile din Catalog sa se reflecte si in fereastra (cu exceptia paginii
Teacher/Assistant) trebuie_ **_sa va delogati si sa va logati_** _din nou!_

### Student

![alt text](https://scontent.fcra2-1.fna.fbcdn.net/v/t1.15752-9/334881689_1155234195142693_742454445323488000_n.png?_nc_cat=105&ccb=1-7&_nc_sid=ae9488&_nc_ohc=P0ORTWgh9Y8AX_owCKW&_nc_ht=scontent.fcra2-1.fna&oh=03_AdR3PsMT8T9hZ6ggI_hg0dt0KEMynGnJMjZi7d1r4PHTpQ&oe=6436F31C)

Aici studentul poate vedea cursurile la care este inrolat si notele obtinute la acel curs. Cand
apasa pe el acesta poate vedea toate detaliile aferente cursului revelente lui.


### Teacher/Assistant

![alt text](https://scontent.fcra2-1.fna.fbcdn.net/v/t1.15752-9/335590626_6176795475675191_695109255663343573_n.png?_nc_cat=107&ccb=1-7&_nc_sid=ae9488&_nc_ohc=um1FO0vb8Z4AX_-rF3e&_nc_ht=scontent.fcra2-1.fna&oh=03_AdQTH55BYyGNphXjzP--Mk0NydmtcOm7D6E6b53tebRIqw&oe=6436F174)

Aici profesorul/asistentul poate sa vada detalii despre toate cursurile din care acesta face
parte. Apasand pe curs, acesta are la dispozitie un tabel cu toti studentii in care acesta
poate sa treaca nota( examen – profesor ; partial – asistent)

![alt text](https://scontent.fcra2-1.fna.fbcdn.net/v/t1.15752-9/335925471_1192548608293720_183139217291193915_n.png?_nc_cat=109&ccb=1-7&_nc_sid=ae9488&_nc_ohc=mwsZeOCE0esAX_ip1qZ&_nc_ht=scontent.fcra2-1.fna&oh=03_AdTEkc78ofspU8OTSQtsaFle3u4698IkW2ehVSNDVZDmIw&oe=643704B3)

Daca acesta incearca sa editeze un camp ce nu ii intra sub jurisdictie atunci se va afisa
mesajul:

![alt text](https://scontent.fcra2-1.fna.fbcdn.net/v/t1.15752-9/333203774_2089211617939744_926957779176936032_n.png?_nc_cat=107&ccb=1-7&_nc_sid=ae9488&_nc_ohc=8JoqdUp2ngAAX9a6CAG&_nc_ht=scontent.fcra2-1.fna&oh=03_AdR6sirnQCl_J7YFevvW37WYotawX45w532SquddC7MThQ&oe=6437048D)


Odata ce a terminat de introdus notele acesta are butonul de validare care v-a trasmite
schimbarile catre catalog si va notifica partintii de notele studentilor.

![alt text](https://scontent.fcra2-1.fna.fbcdn.net/v/t1.15752-9/335912024_157901387139545_3642781204960923204_n.png?_nc_cat=101&ccb=1-7&_nc_sid=ae9488&_nc_ohc=18ApMHZrsdIAX-L2Khp&_nc_ht=scontent.fcra2-1.fna&oh=03_AdQG56rZ-Z4dyhTDh0EOasYqKT7c6wr-V8Xfci5sobTqGw&oe=6436EE25)

### Parent

Aici un parinte primeste email-uri de la facultate cu notele obtinute de elev.

Dand click pe email acesta poate vedea continutul sau.

![alt text](https://scontent.fcra2-1.fna.fbcdn.net/v/t1.15752-9/336381858_1248971439388553_332189985290978041_n.png?_nc_cat=109&ccb=1-7&_nc_sid=ae9488&_nc_ohc=Bp3ovNE-M4EAX-dYKt4&_nc_ht=scontent.fcra2-1.fna&oh=03_AdS70ualk1NyRvLwZ27xLw893QO1sYREmVUoAprROxe_AA&oe=6436FDB7)



