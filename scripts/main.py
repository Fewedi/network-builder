import os

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

from fileprocesser import RunFiles


def runListToDf(run_list):
    df = pd.DataFrame(columns=run_list[0].getRow().columns.values)
    for rf in run_list:
        df.loc[len(df)] = rf.getRow().iloc[0]
    return df


def subPlotSum(fig, df, row, col, place, cat, val, x):
    dic = {"testcap": "Tests pro Tag", "users": "Appnutzer"}
    plt.subplot(row, col, place)
    plt.title(dic[str(cat)] + " = " + str(round(float(val), 4)))
    df1 = df[df[cat] == val]
    df1 = df1.sort_values(by=x)
    colors = {'true': 'tab:blue', 'false': 'tab:orange'}
    if place % col != 1:
        plt.tick_params('y', labelleft=False)
    names = df1[x]
    col = df1["prio"]
    values = df1["sum"]
    maximum = max(df["sum"])
    plt.scatter(names.astype(str), values, c=col.map(colors), marker='.', alpha=0.5)
    plt.ylim((0, maximum + maximum * 0.1))


def plotSumPotsForTest(directories, path):
    run_list = []
    for dir in directories:
        if "users-testcap-pri" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rf = RunFiles(l, dir, "infectionsSum")
            run_list.append(rf)
    df = runListToDf(run_list)
    r = 1
    c = 7
    column = df['testcap'].unique()
    column.sort()
    fig = plt.figure(figsize=(8,1.5))
    pos = 0
    for value in column:
        pos = pos + 1
        subPlotSum(fig, df, r, c, pos, "testcap", str(value), "users")
    plt.rcParams.update({'font.size': 4})
    fig.set_dpi(400)
    plt.show()
    fig.savefig(os.path.join(os.path.dirname(__file__), "tests.svg"), format="svg", bbox_inches='tight', pad_inches=0)
    fig.savefig(os.path.join(os.path.dirname(__file__), "tests.png"), format="png", dpi=300)


def plotSumPlotsForUsers(directories, path):
    run_list = []
    for dir in directories:
        if "users-testcap-pri" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rf = RunFiles(l, dir, "infectionsSum")
            run_list.append(rf)
    df = runListToDf(run_list)
    r = 2
    c = 3
    column = df['users'].unique()
    column.sort()
    plt.rcParams.update({'font.size': 4})
    fig = plt.figure()
    pos = 0
    for value in column:
        pos = pos + 1
        subPlotSum(fig, df, r, c, pos, "users", str(value), "testcap")
    fig.set_dpi(400)
    plt.show()
    fig.savefig(os.path.join(os.path.dirname(__file__), "users.svg"), format="svg", bbox_inches='tight', pad_inches=0)
    fig.savefig(os.path.join(os.path.dirname(__file__), "users.png"), format="png", dpi=300)


def plotForProb(df, s):
    np.linspace(0, 2 * np.pi, 100)
    df["probability"] = df["probability"].astype(float)
    df = df.sort_values("probability", ascending=False)
    x = df["probability"].to_numpy()
    y = df["sum"].to_numpy()
    fig, ax = plt.subplots()
    ax.plot(x, y)
    ax.set_xlabel("Infektionswahrscheinlichkeit bei Kontakt")
    ax.set_ylabel("Summe an Infektionen")
    ax.set_title("Infektionswahrscheinlichkeiten")
    plt.show()
    fig.savefig(os.path.join(os.path.dirname(__file__), "plot.svg"), format="svg")
    fig.savefig(os.path.join(os.path.dirname(__file__), "plot.png"), format="png", dpi=300)


def plotPopOverTime(df, s):
    fig, ax = plt.subplots()
    for file in df:
        y = file.getMeanCol().to_numpy()
        x = [i for i in range(1, len(y) + 1)]
        ax.plot(x, y, label=file.getName())
    ax.set_xlabel("Tag")
    ax.set_ylabel("Infektiöser Anteil an Population")
    plt.legend()
    plt.show()
    fig.savefig(os.path.join(os.path.dirname(__file__), "lines.svg"), format="svg")
    fig.savefig(os.path.join(os.path.dirname(__file__), "lines.png"), format="png", dpi=300)


def plotForProbOverTime(df, s):
    fig, ax = plt.subplots()
    for file in df:
        y = file.getMeanCol().to_numpy()
        x = [i for i in range(1, len(y) + 1)]
        ax.plot(x, y, label=file.getName())
    ax.set_xlabel("Tag")
    ax.set_ylabel("Infektiöse Agenten")
    plt.legend()
    plt.show()
    fig.savefig(os.path.join(os.path.dirname(__file__), "lines.svg"), format="svg")
    fig.savefig(os.path.join(os.path.dirname(__file__), "lines.png"), format="png", dpi=300)


def plotForPop(df, s):
    np.linspace(0, 2 * np.pi, 100)
    df["population"] = df["population"].astype(float)
    df = df.sort_values("population", ascending=False)
    y = (df["sum"] / df["population"]).to_numpy()
    x = df["population"].astype(int).astype(str).to_numpy()
    fig, ax = plt.subplots()
    ax.bar(x, y)
    ax.set_xlabel("Population", labelpad=10)
    ax.set_ylabel("Infektionen / Population", labelpad=10)
    ax.xaxis.set_label_coords(0.50, -0.1)
    ax.tick_params(axis='x', rotation=20)
    plt.show()
    fig.savefig(os.path.join(os.path.dirname(__file__), "bars.svg"), format="svg")
    fig.savefig(os.path.join(os.path.dirname(__file__), "bars.png"), format="png", dpi=300)


def forPop(directories, path):
    run_list1 = []
    for dir in directories:
        if "population" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rf = RunFiles(l, dir, "infectious")
            run_list1.append(rf)
    plotPopOverTime(run_list1, "infectious")
    run_list2 = []
    for dir in directories:

        if "population" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rf = RunFiles(l, dir, "infectionsSum")
            run_list2.append(rf)
    plotForPop(runListToDf(run_list2), "infectionsSum")


def plotBehaviorOverTime(df, s, name, ydescr):
    fig, ax = plt.subplots()
    dcol = {"true-true": "blue", "false-true": "darkblue",
            "true-false": "green", "false-false": "darkgreen"}
    dshape = {"true-true": 'dashed', "false-true": 'solid',
              "true-false": 'dashed', "false-false": 'solid'}
    dshape = {"true-true": 'x', "false-true": 'o',
              "true-false": 'x', "false-false": 'o'}
    dname = {"true-true": 'if inf - if test', "false-true": 'not if inf - if test',
             "true-false": 'if inf - not if test', "false-false": 'not if inf - not if test'}

    for file in df:
        y = file.getMeanCol().to_numpy()
        x = [i for i in range(1, len(y) + 1)]
        ax.scatter(x, y, label=dname[file.getName()], color=dcol[file.getName()], marker=dshape[file.getName()])
    ax.set_xlabel("Tag")
    ax.set_ylabel(ydescr)
    plt.legend()
    plt.show()

    fig.savefig(os.path.join(os.path.dirname(__file__), name + ".svg"), format="svg")
    fig.savefig(os.path.join(os.path.dirname(__file__), name + ".png"), format="png", dpi=300)


def plotForBehavior(df, param1):
    np.linspace(0, 2 * np.pi, 100)
    df["allreadyinfected-allreadytested"] = df["allreadyinfected"] + "-" + df["allreadytested"]
    df = df.sort_values("allreadyinfected-allreadytested", ascending=False)
    y = (df["sum"]).to_numpy()
    x = df["allreadyinfected-allreadytested"].to_numpy()
    fig, ax = plt.subplots()
    ax.bar(x, y)
    ax.set_xlabel("Agenten testen sich, wenn sie bereits: infiziert waren - positiv getestet waren", labelpad=10)
    ax.set_ylabel("Infektionen / Population", labelpad=10)
    ax.xaxis.set_label_coords(0.50, -0.1)
    plt.show()
    fig.savefig(os.path.join(os.path.dirname(__file__), "bars.svg"), format="svg")
    fig.savefig(os.path.join(os.path.dirname(__file__), "bars.png"), format="png", dpi=300)


def forBehaviour(directories, path):
    run_list1 = []
    s = "positiveTestsArrivedToday"
    for dir in directories:
        if "allreadyinfected-allreadytested" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rf = RunFiles(l, dir, s)
            run_list1.append(rf)
    plotBehaviorOverTime(run_list1, s, "positivetests", "Anzahl positiver Tests")
    run_list3 = []
    s = "negativeTestsArrivedToday"
    for dir in directories:
        if "allreadyinfected-allreadytested" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rf = RunFiles(l, dir, s)
            run_list3.append(rf)
    plotBehaviorOverTime(run_list3, s, "negativetests", "Anzahl negativer Tests")


def forInfectionstatus(directories, path):
    cols = ["susceptible", "incubation", "asymptomatic", "mild", "severe", "severeHos", "dead", "recovered"]
    for dir in directories:
        if "allreadyinfected-allreadytested" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rfList = []
            for s in cols:
                rf = RunFiles(l, dir, s)
                rfList.append(rf)

    plotPopOverTime(runListToDf(rfList), "hä")



def forProb(directories, path):
    run_list1 = []
    for dir in directories:
        if "probability" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rf = RunFiles(l, dir, "infectious")
            run_list1.append(rf)
    plotForProbOverTime(run_list1, "infectious")
    run_list2 = []
    for dir in directories:
        if "probability" in dir:
            n = os.path.join(path, dir)
            file_list = [f for f in os.listdir(n) if f.endswith('.csv')]
            l = []
            for f in file_list:
                p = os.path.join(n, f)
                csv_data = pd.read_csv(p, sep=';')
                l.append(csv_data)
            rf = RunFiles(l, dir, "infectionsSum")
            run_list2.append(rf)
    plotForProb(runListToDf(run_list2), "infectionsSum")


def decide():
    path = os.path.join(os.path.dirname(__file__), "data")
    files_and_directories = os.listdir(path)
    directories = [f for f in files_and_directories if os.path.isdir(os.path.join(path, f))]
    if "probability" in directories[0] or "probability" in directories[1]:
        forProb(directories, path)
    elif "population" in directories[0] or "population" in directories[1]:
        forPop(directories, path)
    elif "allreadyinfected-allreadytested_" in directories[0] or "allreadyinfected-allreadytested_" in directories[1]:
        forBehaviour(directories, path)
        forInfectionstatus(directories, path)
    elif "users-testcap-prio" in directories[0] or "users-testcap-prio" in directories[1]:
        plotSumPlotsForUsers(directories, path)
        plotSumPotsForTest(directories, path)


decide()
