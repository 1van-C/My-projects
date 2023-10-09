from math import sqrt

def color(x):
    """ Function to determine the apparent color of a shirt, according to the wavelength it generates at a certain speed.

    Args:
        x (integer): Speed of the wearer of the shirt.

    Returns:
        string: The color of the shirt that would be observed.
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