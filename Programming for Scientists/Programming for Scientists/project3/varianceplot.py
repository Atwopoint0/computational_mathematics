import matplotlib.pyplot as plt
import numpy as np
fig = plt.figure()
ax = fig.add_subplot()

range   = np.loadtxt("variance.data", usecols = (0, ))
genData = np.loadtxt("variance.data", usecols = (1, ))
triData = np.loadtxt("variance.data", usecols = (2, ))

plt.plot(range, np.log(genData))
plt.plot(range, np.log(triData))
plt.xlabel("matrix dimension")
plt.ylabel("log(variance)")
plt.show()