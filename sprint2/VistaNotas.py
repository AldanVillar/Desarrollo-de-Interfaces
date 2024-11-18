import tkinter as tk
from tkinter import MULTIPLE

class VistaNotas:
    root = tk.Tk()
    root.title("Ventana")
    root.geometry("300x400")

    label = tk.Label(root, text="Visualizador de notas")
    label.pack()
    entrada = tk.Entry(root, width=30)
    entrada.pack()

    listbox = tk.Listbox(root, selectmode=MULTIPLE)
    listbox.pack(pady=10)

    boton = tk.Button(root, text="Agregar nota")
    boton.pack()
    boton = tk.Button(root, text="Eliminar nota")
    boton.pack()
    boton = tk.Button(root, text="Obtener nota")
    boton.pack()
    boton = tk.Button(root, text="Guardar notas")
    boton.pack()
    boton = tk.Button(root, text="Cargar notas")
    boton.pack()
    label = tk.Label(root, text="")
    label.pack()
    root.mainloop()