🛒 Java projekt - Biblioteksystem

Detta är ett skolprojekt där jag byggt ett bibliotekssystem i java, med ett grafiskt gränssnitt i swing.
---

## 🚀 Kom igång lokalt

Följ dessa steg för att installera och köra projektet på din egen dator.


````bash
# I projektets root
clone repo

# Sätt upp env filen i src root
skapa database.properties

# Installera för backend
Kör main
````

---


## ✨ Funktioner
- [x] Se tillgängliga böcker
- [x] Söka och filtrera bland böcker
- [x] Låna en bok
- [x] Lämna tillbaka böcker
- [x] Förlänga sina lån
- [x] Se en profilsida med info om lån
- [X] Uppdatera sin info på profilsidan
- [X] Fixa felhantering om medlem inte har ngt lån och öppnar mina sidor.
---
- [x] Skapa nya låntagarkonton
- [x] Se alla aktiva lån
- [x] Lägga till en bok i sortimentet
- [x] Redigera en bok i sortimentet
- [x] Ta bort en bok ur sortimentet
- [X] Lägga till författare
- [x] Redigera författare
- [X] Lägga böcker i kategorier
---

## 📂 Projektstruktur

```plaintext
.
├── form/         # Input swing komponenter
├── model/        # Klasser, book, member,loan,author
├── repository/  # Hämtar updaterar raderar veriferar med databas
├── service      # Modul för ui att kalla på, som sedan gör databas anrop
├── ui          # Swing komponenter
└─ README.md     # Projektdokumentation
└─ Main          # Startar programet
```
