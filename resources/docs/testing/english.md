# Testing

This document explains the testing strategy and coverage used for Planos y Centellas.

## Unit testing

There is unit testing for the following packages:

* **api**
* **repository**
* **viewmodel**

### api

This module is in charge of obatining the data from the web. Contains only one class, *Provider*

All the methods of this class have their corresponding unit test. The exception is *searchEpisodes()* which is not implemented yet, it only calls to *getEpisodes()*.

### repository

This module obtains the information from the **Provider** class of the **api**. Contains only one class, repository.


For this class, all the methods related to LiveData are tested. We ensure that setValue() triggers the onChange() method. I followed this link: https://medium.com/androiddevelopers/unit-testing-livedata-and-other-common-observability-problems-bb477262eb04

On the other hand, there are some AsynTask inner classes that use an instance of Provider in their *doInBackground* methods, and update MutableLiveData using setValue in *onPostExecute*.

As those interaction have been tested already, we consider those classes tested.

### viewmodel

This module is in charge of persisting the data used by the UI when there are changes in configuration (such as rotations). Contains only one class, *HomeViewModel*

This class has an instance of Repository to obtain the required data. Testing for all its methods have been added, although they only are calls to Repository methods that have been already tested