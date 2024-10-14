import tkinter as tk
def cambio():
    label3.config(text="Cambiado")

root = tk.Tk()
root.title("Ventana")
root.geometry("300x200")
frame = tk.Frame(root)
frame.pack(fill='both', expand=True, padx = 10, pady = 10)
label1 = tk.Label(root, text="¡Bienvenido!")
label1.pack()
label2 = tk.Label(root, text="Aldán Villar Silva")
label2.pack()
label3 = tk.Label(root, text="Cambia con el botón")
label3.pack()
boton = tk.Button(frame, text = "Cambia el 3º texto", command = cambio)
boton.pack(pady = 5)
root.mainloop()