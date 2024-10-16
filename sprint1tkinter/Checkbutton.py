import tkinter as tk

def seleccionado():
    seleccion1 = var_check1.get()
    seleccion2 = var_check2.get()
    seleccion3 = var_check3.get()
    Leer = "Leer" if seleccion1 else ""
    Deporte = "Deporte" if seleccion2 else ""
    Musica = "Música" if seleccion3 else ""
    seleccion.config(text = "Seleccionado: " + Leer + " " + Deporte + " " +Musica)

root = tk.Tk()
root.title("Ventana")
root.geometry("400x200")

var_check1 = tk.IntVar()
var_check2 = tk.IntVar()
var_check3 = tk.IntVar()
check1 = tk.Checkbutton(root, text = "Leer", variable = var_check1, command = seleccionado)
check1.pack(pady = 20)
check2 = tk.Checkbutton(root, text = "Deporte", variable = var_check2, command = seleccionado)
check2.pack(pady = 10)
check3 = tk.Checkbutton(root, text = "Música", variable = var_check3, command = seleccionado)
check3.pack(pady = 0)
seleccion = tk.Label(root, text="Seleccionado: ")
seleccion.pack(pady = 10)
root.mainloop()