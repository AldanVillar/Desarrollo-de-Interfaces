from PIL import Image, ImageTk
from urllib import response
import requests
import io

def descargar_imagen(url, size):
    try:
        response = requests.get(url)
        response.raise_for_status()

        image = Image.open(io.BytesIO(response.content))
        image = image.resize(size, Image.LANCZOS)
        image_tk = ImageTk.PhotoImage(image)

        return image_tk

    except requests.exceptions.RequestException as e:
        print(f"Error al descargar la imagen desde la URL: {url}")

    except Exception as e:
        print(f"Error al procesar la imagen: {e}")

