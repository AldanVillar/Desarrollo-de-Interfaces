from sprint0python.Monstruo import Monstruo


class Heroe:
    def __init__(self,nombre_heroe):
        self.nombre = nombre_heroe
        self.ataque = 20
        self.defensa = 5
        self.salud = 100
        self.salud_maxima = 100

    def atacar(self, enemigo):
        print(self.nombre + " ataca a "+ enemigo.nombre)
        enemigo.salud = enemigo.salud + enemigo.defensa - self.ataque

    def curarse(self):
        self.salud + 10
        if self.salud > self.salud_maxima:
            self.salud = self.salud_maxima
        print("Héroe se ha curado. Salud actual: " + self.salud)

    def defenderse(self):
        self.defensa = self.defensa + 5


