from sprint0python.Heroe import Heroe


class Monstruo:
    def __init__(self):
        self.nombre = "Nelo angelo"
        self.ataque = 10
        self.defensa = 10
        self.salud = 50
        self.salud_maxima = 50

    def atacar(self, heroe):
        heroe.salud = heroe.salud + heroe.defensa - self.ataque