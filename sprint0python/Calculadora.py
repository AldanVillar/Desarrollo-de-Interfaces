from sprint0python.operaciones import multiplicacion, division, suma, resta
while True:
    op = 0
    otro = 0
    n1 = int(input("Elige un número: "))
    n2 = int(input("Elige otro número: "))
    print("1.Suma")
    print("2.Resta")
    print("3.Multiplicación")
    print("4.División")
    print("")
    while True:
        op =  int(input("Escoge una opción(1-4): "))
        if op >= 1 and op <= 4:
            break
    if op == 1:
        suma(n1,n2)
    if op == 2:
        resta(n1,n2)
    if op == 3:
        multiplicacion(n1,n2)
    if op == 4:
        division(n1,n2)
    while True:
        otro = input("Otra operación?(S/N)")
        if otro == "S" or otro == "N" or otro == "s" or otro == "n":
            break
    if otro == "N" or otro == "n":
        break