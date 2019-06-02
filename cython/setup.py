#!/usr/bin/python3
from distutils.core import setup
from Cython.Build import cythonize

setup(name='TSP',
      ext_modules=cythonize("tsp.pyx"))