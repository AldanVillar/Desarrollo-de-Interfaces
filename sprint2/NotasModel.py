import tkinter as tk
import VistaNotas

class NotasModel:
    def __init__(self):
        self.notas = []

    def agregar_nota(nueva_nota):
        VistaNotas.listbox.append(nueva_nota)

    def eliminar_nota(indice, self):
        del self.notas[indice]

    def obtener_notas(self):
        return self.notas

    def guardar_notas(self):
        print()

    def cargar_notas(self):
        print()