#pragma once
#include "GameObject.h"
#include "GameManager.h"
class Enemy : public GameObject //���ӿ�����Ʈ�� ��ӹ���
{
public:
	Enemy(COORD startPos, GameManager* _manager);
	~Enemy();

	void tick();
	void die();
	void OnCollision();
	static int movePosX;
private:
	GameManager* manager;
	int time;
};

