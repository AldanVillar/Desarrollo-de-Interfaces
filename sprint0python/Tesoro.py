from random import randint


class Tesoro:
    def __init__(self):
        self.aumentoAtq = 5
        self.aumentoDef = 10

    def encontrar_tesoro(self, heroe):
        numero = randint(1,3)
        if numero == 1:
            heroe.ataque = heroe.ataque + self.aumentoAtq
            print("Se ha aumentado tu ataque en: " + self.aumentoAtq + " puntos")
        elif numero == 2:
            heroe.defensa = heroe.defensa + self.aumentoDef
            print("Se ha aumentado tu defensa en " + self.aumentoDef + " puntos")
        else:
            heroe.salud = heroe.salud_maxima
            print("La salud se ha recuperado")