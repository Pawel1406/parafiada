# --- ETAP 1: Budowanie aplikacji (Maven + JDK 21) ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Kopiujemy plik konfiguracyjny zależności
COPY pom.xml .
# Kopiujemy kod źródłowy
COPY src ./src

# Budujemy plik JAR, pomijając testy dla oszczędności czasu
RUN mvn clean package -DskipTests

# --- ETAP 2: Lekki obraz uruchomieniowy (tylko JRE 21) ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Kopiujemy zbudowany plik JAR z poprzedniego etapu
COPY --from=build /app/target/*.jar app.jar

# Informujemy kontener, na jakim porcie aplikacja nasłuchuje
EXPOSE 8080

# Komenda uruchamiająca aplikację
ENTRYPOINT ["java", "-jar", "app.jar"]