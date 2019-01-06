
job("drain-job") {
  label('cb2018')
  
  throttleConcurrentBuilds {
      maxPerNode(1)
      maxTotal(1)
      categories(['CB',])
  }

  wrappers {
      colorizeOutput()
      timestamps()
  }

  publishers {
    chucknorris()
  }

  steps { shell('docker run vladistan/cb2018t5:0.1 /p/find_ips 114 1') }
}


static String[] getSlices(n) {
        return  (0..n).collect { "${it}" }
}

matrixJob("vlad-task-5") {

     throttleConcurrentBuilds {
            categories(['CB',])
            throttleMatrixConfigurations(false)

            maxPerNode(1)
            maxTotal(1)
    }
  
    axes {
        label('node', ['cb2018',])
        text('SLICE1', getSlices(255))
        text('SLICE2', getSlices(3))
    }
  
    steps {
      shell('docker run vladistan/cb2018t5:0.2 /p/find_ips ${SLICE1} ${SLICE2}')
    }
   
}

