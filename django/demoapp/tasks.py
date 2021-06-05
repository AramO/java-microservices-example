from datetime import datetime

# Celery
from celery import shared_task
from random import randint
from celery.task import periodic_task
from celery.schedules import crontab
from celery.utils.log import get_task_logger
logger = get_task_logger(__name__)

@shared_task
def display_time():
    print("The time is %s :" % str(datetime.now()))
    return True

@periodic_task(run_every=(crontab(minute='*/1')),
               name='task_generate_random_number',
               ignore_result=True
               )
def task_generate_random_number():
    print randint(1, 100)
    logger.info("Random number generated")