import tkinter as tk

def crearTexto():
    texto.config(text = cuadroTexto.get("1.0", tk.END))

def borrarTexto():
    texto.config(text = "")

root = tk.Tk()
root.title("Ventana")
root.geometry("600x500")
frame1 = tk.Frame(root)
frame1.pack(fill='both', expand=True, padx = 10, pady = 10)
frame2 = tk.Frame(root)
frame2.pack(fill='both', expand=True, padx = 10, pady = 10)

label1 = tk.Label(frame1, text="Campo de")
label1.pack()
label2 = tk.Label(frame1, text="Entrada")
label2.pack()
cuadroTexto = tk.Text(frame1, height=10, width=40)
cuadroTexto.pack(pady=5)
boton = tk.Button(frame2, text = "Generar texto", command = crearTexto)
boton.pack(pady = 5)
boton = tk.Button(frame2, text = "Borrar texto", command = borrarTexto)
boton.pack(pady = 5)
texto = tk.Label(frame2, text="")
texto.pack()
root.mainloop()