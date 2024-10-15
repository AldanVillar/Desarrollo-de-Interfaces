import tkinter as tk

def crear_texto():
    texto.config(text="Mensaje")
def cerrar_ventana():
    root.destroy()

root = tk.Tk()
root.title("Ventana")
root.geometry("300x200")
frame = tk.Frame(root)
frame.pack(fill='both', expand=True, padx = 10, pady = 10)
texto = tk.Label(root, text="")
texto = tk.Label(root, text="Aldán Villar Silva")
texto.pack()
boton = tk.Button(frame, text = "Crear texto", command = crear_texto)
boton.pack(pady = 5)
boton = tk.Button(frame, text = "Cerrar ventana", command = cerrar_ventana)
boton.pack(pady = 5)
root.mainloop()