import tkinter as tk
from tkinter import messagebox

import NotasModel
import VistaNotas
from requests import delete

from VistaNotas import VistaNotas


class ControladorNotas:


    def agregar_nota(self):
        NotasModel.agregar_nota(VistaNotas.entrada.get())
        self.actualizar_listbox()

    def eliminar_nota(self):
        self.VistaNotas.listbox.curselection()
        NotasModel.eliminar_nota()
        self.actualizar_listbox()

    def guardar_notas(self):
        NotasModel.guardar_notas()
        messagebox.showinfo("Guardado con éxito")

    def cargar_notas(self):
        NotasModel.cargar_notas()
        self.actualizar_listbox()

    def descargar_imagen(self):


    def actualizar_coordenadas(event):


    def actualizar_listbox(self):
        delete(0, tk.END)
        NotasModel.obtener_notas()
        insert(tk.END, nota)
