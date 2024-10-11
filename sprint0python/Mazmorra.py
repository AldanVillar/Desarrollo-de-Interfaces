from sprint0python.Monstruo import Monstruo
from sprint0python.Tesoro import Tesoro


class Mazmorra:
    def __init__(self,Miheroe):
        self.heroe = Miheroe
        self.monstruos = 5
        self.tesoro = Tesoro()

    def jugar(self):
        print("Héroe entra en la mazmorra.")
        while True:
            self.monstruos = self.monstruos - 1
            if self.monstruos > 0 or self.heroe.salud > 0:
                print("Te has encontrado con un Nelo angelo")
                self.enfrentar_enemigo()
            elif self.monstruos == 0:
                print("¡" + self.heroe.nombre + " ha derrotado a todos los monstruos y ha conquistado la mazmorra!")
            else:
                print("Héroe ha sido derrotado en la mazmorra.")
            break

    def enfrentar_enemigo(self):
        enemigo = Monstruo()
        turno = True
        while True:
            if self.heroe.salud > 0 and enemigo.salud > 0:
                if turno == True:
                    print("¿Qué deseas hacer?")
                    print("1. Atacar")
                    print("2. Defender")
                    print("3. Curarse")
                    while True:
                        opcion = int(input("Escoge una opción(1-3): "))
                        if opcion >= 1 and opcion <= 3:
                            break
                    if opcion == 1:
                        self.heroe.ataque(enemigo)
                    if opcion == 2:
                        self.heroe.defenderse()
                    if opcion == 3:
                        self.heroe.curarse()
                    turno = False
                else:
                    enemigo.atacar(self.heroe)
                    turno = True
            if enemigo.salud == 0:
                print("Enemigo derrotado")
                self.buscar_tesoro()
                break
            if self.heroe.salud == 0:
                break
    def buscar_tesoro(self):
        tesoro = Tesoro()
        print(self.heroe.nombre + " ha encontrado un tesoro")
        tesoro.encontrar_tesoro(self.heroe)

