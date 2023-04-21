from math import sqrt

def color(x):
    """Función para determinar el color aparente de una playera según la longitud de onda que se generaría si se llevara puesta a cierta velocidad.

    Args:
        x (integer): Velocidad del portador de la camiseta.

    Returns:
        string: El color de la playera que vería un observador.
    """
    c = 3*10**8
    if x == c or x == -c:
        return ("invisivel")
    try:
        y = 700 * (sqrt((c-x) / (c+x))-1) + 700
        if y < 400:
            return "invisivel"
        elif y < 425:
            return "violeta"
        elif y < 445:
            return "anil"
        elif y < 500:
            return "azul"
        elif y < 575:
            return "verde"
        elif y < 585:
            return "amarelo"
        elif y < 620:
            return "laranja"
        elif y < 750:
            return "vermelho"
        else:
            return "invisivel"
    except ValueError:
        return "invisivel"

while True:
    try:
        vel = int(input())
        print(color(vel))
        break
    except EOFError:
        print("vermelho")
        break