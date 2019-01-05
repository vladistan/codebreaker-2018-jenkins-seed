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

  steps { shell('sleep 10m') }
}
