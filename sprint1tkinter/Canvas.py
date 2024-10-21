import tkinter as tk

def crear():
    rectangulo = int(entrada1.get())
    circulo = int(entrada2.get())
    canvas.create_rectangle(50, 0, 150, rectangulo, width=2)
    canvas.create_oval(250, 0, 350, circulo, fill="black", width=2)

root = tk.Tk()
root.title("Ventana")
root.geometry("600x500")
canvas = tk.Canvas(root, width=400, height=200, bg="white")
canvas.grid(row = 4, column = 1, padx = 10, pady = 10)

DRect = tk.Label(root, text="Dimensiones rectángulo:")
DRect.grid(row = 1, column = 0, padx = 10, pady = 10)
entrada1 = tk.Entry(root, width=30)
entrada1.grid(row = 1, column = 1, padx = 10, pady = 10)
Dcirc = tk.Label(root, text="Dimensiones circulo:")
Dcirc.grid(row = 2, column = 0, padx = 10, pady = 10)
entrada2 = tk.Entry(root, width=30)
entrada2.grid(row = 2, column = 1, padx = 10, pady = 10)
boton = tk.Button(root, text = "Crear", command = crear)
boton.grid(row = 3, column = 1, padx = 2, pady = 10)


root.mainloop()