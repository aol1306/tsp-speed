FROM python
WORKDIR /tsp
COPY . .
RUN pip install git+https://github.com/cython/cython.git && \
  python setup.py build_ext --inplace
CMD ["python3", "main.py", "coords.txt"]
