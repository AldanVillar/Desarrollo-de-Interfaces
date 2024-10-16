import tkinter as tk

def cambio_de_color():
    root.config(bg = var_radio.get())

root = tk.Tk()
root.title("Ventana")
root.geometry("400x200")

var_radio = tk.StringVar()
var_radio.set("Rojo")
check1 = tk.Radiobutton(root, text = "Rojo", variable = var_radio, value = "red",command = cambio_de_color)
check1.pack(pady = 20)
check2 = tk.Radiobutton(root, text = "Verde", variable = var_radio, value = "green", command = cambio_de_color)
check2.pack(pady = 10)
check3 = tk.Radiobutton(root, text = "Azul", variable = var_radio, value = "blue", command = cambio_de_color)
check3.pack(pady = 0)
root.mainloop()