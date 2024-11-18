from logging import exception
from tkinter import Image
from urllib import response

import requests
import io

def descargar_imagen(url, size):
    url = requests.get("https://upload.wikimedia.org/wikipedia/en/9/9f/Hellsingalucard.png",size=43)
    try:
        response = requests.get(url)
        response.raise_for_status()

        return print()

    except requests.exceptions.RequestException as e:
        print(f"Error al descargar la imagen desde la URL: {url}")

    except Exception as e:
        print(f"Error al procesar la imagen: {e}")

