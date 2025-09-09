# 🃏 RPG de Cartas en Java

Es un juego de rol por turnos desarrollado en **Java**. 
El objetivo es crear personajes, enfrentarlos en combates y registrar el historial de partidas en un log.  

---

## 📜 Características principales
- **Menú ** en consola:
  - Nueva partida (personajes aleatorios o personajes creados manualmente)
  - Leer logs
  - Borrar logs
  - Salir
- **Generación de personajes** con atributos (HP, Fuerza, Defensa, Agilidad, Velocidad, Nivel).
- **Sistema de combate por turnos**:
  - Ataques calculados según estadísticas.
  - Determinación del atacante por velocidad.
  - Reducción de daño por defensa y posibilidad de evasión.
- **Gestión de logs**:
  - Guardado automático de las partidas en un archivo.
  - Lectura y eliminación desde el menú.

---

## ⚔️ Instrucciones del juego
1. Ejecutar en consola './ejecutar.bat'.
2. Elegir una opción del menú:
   - `1` → Crear personajes y comenzar un combate.
   - `2` → Leer logs guardados.
   - `3` → Borrar logs existentes.
   - `4` → Salir del juego.
3. Los combates se resuelven automáticamente por turnos hasta que uno de los personajes quede sin HP.
4. El ganador sube de nivel y queda registrado en los logs.

