
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
        text('SLICE2', getSlices(7))
    }
  
    steps {
      shell('docker run -t vladistan/cb2018t5:0.3 /p/find_ips ${SLICE1} ${SLICE2}')
    }
   
}

