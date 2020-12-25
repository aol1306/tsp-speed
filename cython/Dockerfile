FROM python
WORKDIR /tsp
COPY . .
RUN pip install cython && \
  python setup.py build_ext --inplace
CMD ["python3", "main.py", "coords.txt"]
