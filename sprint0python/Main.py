from sprint0python.Heroe import Heroe
from sprint0python.Mazmorra import Mazmorra

nombre_heroe = input("Introduce el nombre de tu Héroe: ")
Miheroe = Heroe(nombre_heroe)

mazmorra = Mazmorra(Miheroe)
mazmorra.jugar()