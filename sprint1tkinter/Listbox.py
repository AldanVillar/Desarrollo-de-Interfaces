import tkinter as tk
from tkinter.constants import MULTIPLE

def seleccion():
    seleccion = listbox.curselection()
    elementos = [listbox.get(i) for i in seleccion]
    seleccionado.config(text = f"Selección: {", ".join(elementos)}")

root = tk.Tk()
root.title("Ventana")
root.geometry("400x300")

opciones = ["Manzana", "Plátano", "Melocotón", "Ciruela", "Cereza"]
listbox = tk.Listbox(root, selectmode=MULTIPLE)
for opcion in opciones:
    listbox.insert(tk.END, opcion)
listbox.pack(pady=10)
boton = tk.Button(root, text = "Mostrar Selección", command = seleccion)
boton.pack(pady = 5)
seleccionado = tk.Label(root, text="Selección: ")
seleccionado.pack()
root.mainloop()