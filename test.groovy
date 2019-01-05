job('test-job') {



}

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
