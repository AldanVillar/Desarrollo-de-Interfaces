import tkinter as tk

from Button import texto


def saludo():
    saludar.config(text = "¡Hola "+entrada.get()+", buenos días")

root = tk.Tk()
root.title("Ventana")
root.geometry("400x200")

nombre = tk.Label(root, text="Nombre:")
nombre.grid(row = 0, column = 0, padx = 10, pady = 10)
entrada = tk.Entry(root, width=30)
entrada.grid(row = 0, column = 2, padx = 10, pady = 10)
boton = tk.Button(root, text = "Saludar", command = saludo)
boton.grid(row = 2, column = 2, padx = 2, pady = 10)
saludar = tk.Label(root, text="")
saludar.grid(row = 4, column = 2, padx = 2, pady = 10)
root.mainloop()