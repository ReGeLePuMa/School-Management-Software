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

![alt text](https://github.com/ReGeLePuMa/array-sorting-algorithms/assets/93268175/0ed4f680-bf06-43be-a62e-d962cc9f0f08)


Daca nu corespunde rolul lor cu domeniul adresei de email sau email-ul/parola este gresita
atunci va aparea mesajul cu autentificarea invalida

![alt text](https://github.com/ReGeLePuMa/array-sorting-algorithms/assets/93268175/9139d2ea-5a46-44ff-a04c-0226a57940f0)

Odata autentificat, acestuia i se va afisa pagina corespunzatoare cu rolul acestuia.

_Nota: Inchiderea paginii de logare va duce la_ **_terminarea programului_**_! Puteti in schimb sa
inchideti celelalte ferestre fara probleme._

_Nota: Pentru ca schimbarile din Catalog sa se reflecte si in fereastra (cu exceptia paginii
Teacher/Assistant) trebuie_ **_sa va delogati si sa va logati_** _din nou!_

### Student

![alt text](https://github.com/ReGeLePuMa/array-sorting-algorithms/assets/93268175/cb400d73-ebc5-4901-810b-6df3a33273ab)

Aici studentul poate vedea cursurile la care este inrolat si notele obtinute la acel curs. Cand
apasa pe el acesta poate vedea toate detaliile aferente cursului revelente lui.


### Teacher/Assistant

![alt text](https://github.com/ReGeLePuMa/array-sorting-algorithms/assets/93268175/b117e753-39c0-425d-a822-4d95acdb812e)

Aici profesorul/asistentul poate sa vada detalii despre toate cursurile din care acesta face
parte. Apasand pe curs, acesta are la dispozitie un tabel cu toti studentii in care acesta
poate sa treaca nota( examen – profesor ; partial – asistent)

![alt text](https://github.com/ReGeLePuMa/array-sorting-algorithms/assets/93268175/4e8a4dab-059e-4c07-ab13-c31027d7726f)

Daca acesta incearca sa editeze un camp ce nu ii intra sub jurisdictie atunci se va afisa
mesajul:

![alt text](https://github.com/ReGeLePuMa/array-sorting-algorithms/assets/93268175/2b38aab6-77fd-45e0-a8d8-c19029a3a74e)


Odata ce a terminat de introdus notele acesta are butonul de validare care v-a trasmite
schimbarile catre catalog si va notifica partintii de notele studentilor.

![alt text](https://github.com/ReGeLePuMa/array-sorting-algorithms/assets/93268175/e8a52815-4688-4ce9-959e-4816e35e5da7)

### Parent

Aici un parinte primeste email-uri de la facultate cu notele obtinute de elev.

Dand click pe email acesta poate vedea continutul sau.

![alt text](https://github.com/ReGeLePuMa/array-sorting-algorithms/assets/93268175/11a414a5-1ce0-4cf9-8116-a1970c8230e8)



