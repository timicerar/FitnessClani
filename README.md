# FitnessClani


FitnessClani je fitnes program namenjen za člane fitnes studija. Program je ustvarila ekipa študentov iz [Mariborske Univerze](https://www.um.si) iz [Fakultete za Elektrotehniko, Računalništvo in Informatiko](https://feri.um.si).
Ekipo sestavljajo [Cerar Timotej](https://github.com/timicerar), [Ribič Jože](https://github.com/r1b1c), [Škrinjar Gregor](https://github.com/gregaskrinjar) in [Vovk Timi](https://github.com/timiv1). 

>Program je povezan s [fitnes programom za receptorje in trenerje](https://github.com/timicerar/FitnessUrejanje). Programa uporabljata isto podatkovno bazo in sta namenjena, kot dopolnilo drug drugemu.

## Pogled člana fitnesa
<p align="center">
<img src="https://user-images.githubusercontent.com/23579188/41303774-88efba1a-6e6e-11e8-9123-48ffc4cb3358.png" alt="Slika"/>
<br/>
<img src="https://user-images.githubusercontent.com/23579188/41303784-8d5cf108-6e6e-11e8-928b-f2955735aecf.png" alt="Slika"/>
<br/>
<img src="https://user-images.githubusercontent.com/23579188/41303924-ee6aa418-6e6e-11e8-8cf5-eed4c0de21ba.png" alt="Slika"/>
<br/>
<img src="https://user-images.githubusercontent.com/23579188/41303936-f5849dc6-6e6e-11e8-8ebd-ba4973cf9fab.png" alt="Slika"/>
<br/>
<img src="https://user-images.githubusercontent.com/23579188/41303961-0b926a3a-6e6f-11e8-8065-6a195f5e693f.png" alt="Slika"/>
<br/>
<img src="https://user-images.githubusercontent.com/23579188/41303980-1af58b42-6e6f-11e8-88b8-f2c75b04b064.png" alt="Slika"/>
</p>

## Vsebina
* [Uporaba](#uporaba)
* [Funkcionalnosti](#funkcionalnosti)
* [Vzpostavitev](#vzpostavitev)
* [ER-Diagram](#er-diagram)
* [Avtorji](#avtorji)
* [Licenca](#licenca)

## Uporaba
Namen te aplikacije je komunikacija med trenerji, receptorji in uporabniki fitnes studia.

Aplikacija omogoča:
* Trenerjem, da kreirajo programe, ki jih lahko nato persionalizirajo. Omogočeno jim je dodajanje vaj in urejanje vseh podrobnosti v zvezi z njimi.
* Receptorjem, da dodajajo, odstranjujejo in spremnijajo člane fitnes studia (dostopajo do osnovnih CRUD metod).
* Članom fitnes studija dostop do programov (prijava na programe) in vaj, ki jih trenerji ustvarjajo ter njihove podrobnosti. Člani lahko tudi dodajajo meritve (teža, višina in obseg pasu), ki se prikažejo na grafu.

## Funkcionalnosti
* Dodajanje/urejanje/brisanje članov
* Dodajanje/urejanje/brisanje vadb katerim lahko dodaš postavke (čas trajanja treninga, težo itd.) ko jih dodaš dnevu v programu.
* Dodajanje/urejanje/brisanje programov in dnevov programa
* Dodajanje meritev
* Prijava na program, zaključevanje dnevov v programu, program lahko zaključiš kadar končaš vse dneve programaq
* Pregled vseh vadb / programov / članov

## Vzpostavitev
Za vpostavitev aplikacije si je potrebno namestiti:
* Intellij IDEA Ultimate 2018.1.1. (ta verzija je bila uporabljena v času razvoja)
  * V Intelliju si potrebno naložiti vtičnik / plugin za "Lombok Plugin" (anotacije za getterje in setterje)
  * Potrebno je dodati datasource za JPA (postopek je spodaj)
* Pri kloniranju je potrebno ustvariti Maven projekt.
* Potrebujete strežnik WildFly 12.0.0. Final v katerega je potrebno dodati:
  * JDBC modul za MySQL
    * Ime sheme za podatkovno bazo naj bo "praktikum_db"
  * Uporabnike:
    * Administrativnega uporabnika (user)
    * Aplikacijska uporabnika (trener in receptor)
  * JMS modul (ActiveMQ)
    * Potrebno je dodati sporočilno vrsto (jms/queue/test) in temo (jms/topic/test)

#### Postopek kako klonirati projetk iz GitHuba v Intellij IDEA
1. Kliknite na "Check out from version control" in izberite "Git"
![](https://cdn.discordapp.com/attachments/357945007988015104/456118129240702997/unknown.png)

2. Nato se prijavite s svojim github računom in vpišite URL                                                                           
![](https://cdn.discordapp.com/attachments/357945007988015104/456118773968011276/unknown.png)

3. Nato izberite Maven projekt in klikajte naprej (potrdite vsa pozivna okna) tako dolgo dokler se ne bo odprl Intellij.
![](https://cdn.discordapp.com/attachments/357945007988015104/456118234135920651/unknown.png)

4. Ko se projekt klonira je potrebno omogočiti Maven projekt (pri tem vam pomaga Intellij, v desnem kotu spodaj) 

#### Postopek kako dodati vtičnik za Lombok Plugin
1. Po uspešnem kloniranju projekta kliknite CTRL + ALT + S kateri vam bo odprl nastavitve. Nato pojdite pod "Plugins" in namestite Lombok Plugin (Intellij boste morali ponovno zagnati).
![](https://cdn.discordapp.com/attachments/357945007988015104/456118986145398785/unknown.png)

### Postopek kako dodati Datasource
1. Kliknite na View --> Tool Windows --> Database --> Kliknite na + in izberite MySQL
![datasource](https://user-images.githubusercontent.com/34304505/41399322-4d2ca9be-6fba-11e8-87c0-44733c589a89.PNG)

## ER-Diagram
![35098065_1679825912113232_7251615957863366656_n](https://user-images.githubusercontent.com/23579188/41302266-550436da-6e6a-11e8-9641-4a3df249f0e1.png)

## Avtorji
- [Cerar Timotej](https://github.com/timicerar), [Ribič Jože](https://authenteq.com), [Škrinjar Gregor](https://github.com/gregaskrinjar) and [Vovk Timi](https://github.com/timiv1).

## Licenca
```
   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    ```
